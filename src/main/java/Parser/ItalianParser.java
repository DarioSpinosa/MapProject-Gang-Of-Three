/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Parser;
import java.util.ArrayList;

import Entita.Characters.Character;
import General.Command;
import General.CommandType;
import General.GenericObject;
import General.Name;

public class ItalianParser extends ParserEssentials {

    public ItalianParser(ArrayList<String> articles, ArrayList<String> prepositions) {
        this.articles = articles;
        this.prepositions = prepositions;
    }

    private boolean isCommand(String token, ParserOutput output, ArrayList<Command> commands) {
        boolean confirmed = false;
        for(Command command : commands) {
            if(command.getName().equals(token)|| command.confrontaAlias(token)) {
                confirmed = true;
                output.setCommand(command);
                if(command.getCommandType() == CommandType.PARLA){
                    setLastWordType(WordType.COMANDO_PARLA);
                } else {
                    setLastWordType(WordType.COMANDO);
                }
                break;
            }
        }
        return confirmed;
    }

    private boolean isPreposition(String token, ParserOutput output) {
        boolean confirmed = false;
        if(prepositions.contains(token)) {
            confirmed = true;
            output.setPreposition(token);
            if(lastWordType == WordType.COMANDO_PARLA){
                setLastWordType(WordType.PREPOSIZIONE_PARLA);
            } else {
                setLastWordType(WordType.PREPOSIZIONE);
            }
            lastPreposition = token;
        }
        return confirmed;
    }

    private boolean isArticle(String token) {
        boolean confirmed = false;
        for(String article : articles) {
            if(article.equals(token)) {
                confirmed = true;
                setLastWordType(WordType.ARTICOLO);
                lastArticle = token;
                break;
            }
        }
        return confirmed;
    }

    private boolean isObject(String token, ParserOutput output, ArrayList<GenericObject> objects) {
        boolean confirmed = false;
        for(GenericObject object : objects) {
            if(object.getObjectName().equals(token)) {
                if( (lastWordType == WordType.COMANDO) || (lastWordType == WordType.NOME)){
                    confirmed = true;
                    setLastObject(output, object, objects);
                    break;
                } else if ((lastWordType == WordType.ARTICOLO) && object.isArticleUsable(lastArticle)){
                    confirmed = true;
                    setLastObject(output, object, objects);
                    break;
                } else if ((lastWordType == WordType.PREPOSIZIONE) && object.isPrepositionUsable(lastPreposition)){
                    confirmed = true;
                    setLastObject(output, object, objects);
                    break;
                }
            } else if (object.getAliasHandler().compareAlias(token)) {
                Name tmp = object.getAliasHandler().getAlias(token);
                if( (lastWordType == WordType.COMANDO) || (lastWordType == WordType.NOME)){
                    confirmed = true;
                    setLastObject(output, object, objects);
                    break;
                } else if ((lastWordType == WordType.ARTICOLO) && tmp.getAdmittedArticles().contains(lastArticle)){
                    confirmed = true;
                    setLastObject(output, object, objects);
                    break;
                } else if ((lastWordType == WordType.PREPOSIZIONE) && tmp.getAdmittedPrepositions().contains(lastPreposition)){
                    confirmed = true;
                    setLastObject(output, object, objects);
                    break;
                }
            }
        }
        return confirmed;
    }

    private void setLastObject(ParserOutput output, GenericObject object, ArrayList<GenericObject> objects){
        output.setObject(object);
        setObjectQualities(object, objects);
        lastObject = object;
        setLastWordType(WordType.NOME);
    }

    private boolean isCharacter(String token, ParserOutput output, ArrayList<Character> characters){
        boolean confirmed = false;
        for(Character character : characters){
            if(character.getAliasHandler().getAlias() != null && character.getAliasHandler().compareAlias(token)){
                Name tmp = character.getAliasHandler().getAlias(token);
                if((lastWordType == WordType.COMANDO) || (lastWordType == WordType.COMANDO_PARLA)){
                    confirmed = true;
                    output.setCharacter(character);
                    setLastWordType(WordType.NOME);
                    break;
                } else if ((lastWordType == WordType.PREPOSIZIONE || lastWordType == WordType.PREPOSIZIONE_PARLA) && tmp.getAdmittedPrepositions().contains(lastPreposition)){
                    confirmed = true;
                    output.setCharacter(character);
                    setLastWordType(WordType.NOME);
                    break;
                } else if ((lastWordType == WordType.ARTICOLO) && tmp.getAdmittedArticles().contains(lastArticle)){
                    confirmed = true;
                    output.setCharacter(character);
                    setLastWordType(WordType.NOME);
                    break;
                }
                } else if (character.getName().getName().equalsIgnoreCase(token)){
                    if(lastWordType == WordType.COMANDO || lastWordType == WordType.COMANDO_PARLA){
                        confirmed = true;
                        output.setCharacter(character);
                        setLastWordType(WordType.NOME_PROPRIO);
                        break;
                } else if ((lastWordType == WordType.PREPOSIZIONE || lastWordType == WordType.PREPOSIZIONE_PARLA) && character.isPrepositionUsable(lastPreposition)){
                        confirmed = true;
                        output.setCharacter(character);
                        setLastWordType(WordType.NOME_PROPRIO);
                        break;
                }
            }
        }
        return confirmed;
    }

    private void setObjectQualities(GenericObject object, ArrayList<GenericObject> objects) {
        numberOfObjects++;
        adjectives.clear();
        for(GenericObject obj : objects){
            if(obj.getObjectName().equals(object.getObjectName()) && obj.getAdjectives()!=null && !obj.getAdjectives().isEmpty()) {
                adjectives.addAll(obj.getAdjectives());
            }
        }
    }
    
    private GenericObject searchForObjectBasedOnAdjective(String token, ArrayList<GenericObject> objects){
        for(GenericObject object : objects){
            if(object.getObjectName().equals(lastObject.getObjectName()) && object.getAdjectives().contains(token)){
                return object;
            }
        }
        return null;
    }

    private boolean isAdjective(ParserOutput output, String token, ArrayList<GenericObject> objects) {
        boolean confirmed = false;
        if(adjectives.contains(token)) {
            if(!lastObject.getAdjectives().contains(token)){
                lastObject = searchForObjectBasedOnAdjective(token, objects);
            }
            if(lastObject != null){
                if(output.getSecondObject() == null){
                    output.setFirstObject(lastObject);
                } else {
                    output.setSecondObject(lastObject);
                }
                output.setAdjective(token);
                confirmed = true;
            }
        }
        return confirmed;
    }

    private void setLastWordType(WordType lastWordType) {
        this.lastWordType = lastWordType;
    }

    private void analyzer(String token, ParserOutput output, ArrayList<GenericObject> objects, ArrayList<Character> characters) {
        if(lastWordType == WordType.COMANDO) {
            if(!isArticle(token) && !isObject(token, output, objects)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.ARTICOLO) {
            if(!isObject(token, output, objects) && !isCharacter(token, output, characters)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.PREPOSIZIONE) {
            if(!isObject(token, output, objects) && !isArticle(token)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.NOME) {
            if(!isPreposition(token, output) && !isAdjective(output, token, objects) && !isObject(token, output, objects)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.AGGETTIVO) {
            if(!isPreposition(token, output)) {
                accepted = false;
            }
        } else if (lastWordType == WordType.COMANDO_PARLA) {
            if(!isPreposition(token, output) && !isCharacter(token, output, characters)){
                accepted = false;
            }
        } else if (lastWordType == WordType.PREPOSIZIONE_PARLA) {
            if(!isCharacter(token, output, characters) && !isArticle(token)){
                accepted = false;
            }
        }
    }

    @Override
    public ParserOutput parse(String input, ArrayList<Command> commands, ArrayList<GenericObject> objects, ArrayList<Character> characters) {
        accepted = false;
        adjectives.clear();
        lastArticle = null;
        lastPreposition = null;
        lastObject = null;
        String cmd = input.toLowerCase().trim();
        String[] tokens = cmd.split("\\s+|'(\\s+)?");
        ParserOutput output = new ParserOutput();
        if(isCommand(tokens[0], output, commands)) {
            accepted = true;
            numberOfObjects = 0;
            for(int i = 1; i < tokens.length; i++) {
                analyzer(tokens[i], output, objects, characters);
                if(!accepted) {
                    break;
                }
                if(numberOfObjects > 2) {
                    accepted = false;
                    break;
                }
            }
        }
        if(accepted && output.getFirstObject() != null && output.getFirstAdjective() == null && output.getFirstObject().getAdjectives() != null) {
        	int tmpNumberOfObjects = 0;
        	for(GenericObject oggetto : objects) {
        		if(oggetto.getObjectName().equals(output.getFirstObject().getObjectName())) {
        			tmpNumberOfObjects++;
        			if(tmpNumberOfObjects > 1) {
        				accepted = false;
        				break;
        			}
        		}
        	}
        }
        if(accepted && output.getSecondObject() != null && output.getSecondAdjective() == null && output.getSecondObject().getAdjectives() != null) {
        	int tmpNumberOfObjects = 0;
        	for(GenericObject oggetto : objects) {
        		if(oggetto.getObjectName().equals(output.getSecondObject().getObjectName())){
        			tmpNumberOfObjects++;
        			if(tmpNumberOfObjects > 1) {
        				accepted = false;
        				break;
        			}
        		}
        	}
        }
        if(accepted && lastWordType == WordType.ARTICOLO || lastWordType == WordType.PREPOSIZIONE) {
            accepted = false;
        }
        if(!accepted) {
            return null;
        } else {
            return output;
        }
    }
}
