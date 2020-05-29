/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Parser;
import java.util.ArrayList;

import Entita.Characters.Personaggio;
import General.Command;
import General.CommandType;
import General.GenericObject;
import General.Name;

public class ParserIta extends ParserEssentials {

    public ParserIta(ArrayList<String> articles, ArrayList<String> prepositions) {
        this.articles = articles;
        this.prepositions = prepositions;
    }

    private boolean isCommand(String token, ParserOutput output, ArrayList<Command> commands) {
        boolean confirmed = false;
        for(Command comando : commands) {
            if(comando.getName().equals(token)|| comando.confrontaAlias(token)) {
                confirmed = true;
                output.setCommand(comando);
                if(comando.getCommandType() == CommandType.PARLA){
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
            output.setPreposizione(token);
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

    private boolean isObject(String token, ParserOutput output, ArrayList<GenericObject> oggetti) {
        boolean confirmed = false;
        for(GenericObject oggetto : oggetti) {
            if(oggetto.getNome().equals(token)) {
                if( (lastWordType == WordType.COMANDO) || (lastWordType == WordType.NOME)){
                    confirmed = true;
                    setOggetto(output, oggetto, oggetti);
                    break;
                } else if ((lastWordType == WordType.ARTICOLO) && oggetto.articoloUsabile(lastArticle)){
                    confirmed = true;
                    setOggetto(output, oggetto, oggetti);
                    break;
                } else if ((lastWordType == WordType.PREPOSIZIONE) && oggetto.preposizioneUsabile(lastPreposition)){
                    confirmed = true;
                    setOggetto(output, oggetto, oggetti);
                    break;
                }
            } else if (oggetto.getGestoreAlias().confrontaAlias(token)) {
                Name tmp = oggetto.getGestoreAlias().restituisciAlias(token);
                if( (lastWordType == WordType.COMANDO) || (lastWordType == WordType.NOME)){
                    confirmed = true;
                    setOggetto(output, oggetto, oggetti);
                    break;
                } else if ((lastWordType == WordType.ARTICOLO) && tmp.getArticoli().contains(lastArticle)){
                    confirmed = true;
                    setOggetto(output, oggetto, oggetti);
                    break;
                } else if ((lastWordType == WordType.PREPOSIZIONE) && tmp.getPreposizioni().contains(lastPreposition)){
                    confirmed = true;
                    setOggetto(output, oggetto, oggetti);
                    break;
                }
            }
        }
        return confirmed;
    }

    private void setOggetto(ParserOutput output, GenericObject oggetto, ArrayList<GenericObject> oggetti){
        output.setOggetto(oggetto);
        setObjectQualities(oggetto, oggetti);
        lastObject = oggetto;
        setLastWordType(WordType.NOME);
    }

    private boolean isCharacter(String token, ParserOutput output, ArrayList<Personaggio> personaggi){
        boolean confirmed = false;
        for(Personaggio personaggio : personaggi){
            if(personaggio.getGestoreAlias().getAlias() != null && personaggio.getGestoreAlias().confrontaAlias(token)){
                Name tmp = personaggio.getGestoreAlias().restituisciAlias(token);
                if((lastWordType == WordType.COMANDO) || (lastWordType == WordType.COMANDO_PARLA)){
                    confirmed = true;
                    output.setPersonaggio(personaggio);
                    setLastWordType(WordType.NOME);
                    break;
                } else if ((lastWordType == WordType.PREPOSIZIONE || lastWordType == WordType.PREPOSIZIONE_PARLA) && tmp.getPreposizioni().contains(lastPreposition)){
                    confirmed = true;
                    output.setPersonaggio(personaggio);
                    setLastWordType(WordType.NOME);
                    break;
                } else if ((lastWordType == WordType.ARTICOLO) && tmp.getArticoli().contains(lastArticle)){
                    confirmed = true;
                    output.setPersonaggio(personaggio);
                    setLastWordType(WordType.NOME);
                    break;
                }
                } else if (personaggio.getNome().getName().equalsIgnoreCase(token)){
                    if(lastWordType == WordType.COMANDO || lastWordType == WordType.COMANDO_PARLA){
                        confirmed = true;
                        output.setPersonaggio(personaggio);
                        setLastWordType(WordType.NOME_PROPRIO);
                        break;
                } else if ((lastWordType == WordType.PREPOSIZIONE || lastWordType == WordType.PREPOSIZIONE_PARLA) && personaggio.preposizioneUsabile(lastPreposition)){
                        confirmed = true;
                        output.setPersonaggio(personaggio);
                        setLastWordType(WordType.NOME_PROPRIO);
                        break;
                }
            }
        }
        return confirmed;
    }

    private void setObjectQualities(GenericObject oggetto, ArrayList<GenericObject> oggetti) {
        numberOfObjects++;
        adjectives.clear();
        for(GenericObject obj : oggetti){
            if(obj.getNome().equals(oggetto.getNome()) && obj.getAggettivi()!=null && !obj.getAggettivi().isEmpty()) {
                adjectives.addAll(obj.getAggettivi());
            }
        }
    }
    
    private GenericObject cercaOggettoDaAggettivo(String token, ArrayList<GenericObject> oggetti){
        for(GenericObject oggetto : oggetti){
            if(oggetto.getNome().equals(lastObject.getNome()) && oggetto.getAggettivi().contains(token)){
                return oggetto;
            }
        }
        return null;
    }

    private boolean isAdjective(ParserOutput output, String token, ArrayList<GenericObject> oggetti) {
        boolean confirmed = false;
        if(adjectives.contains(token)) {
            if(!lastObject.getAggettivi().contains(token)){
                lastObject = cercaOggettoDaAggettivo(token, oggetti);
            }
            if(lastObject != null){
                if(output.getSecondoOggetto() == null){
                    output.setPrimoOggetto(lastObject);
                } else {
                    output.setSecondoOggetto(lastObject);
                }
                output.setAggettivo(token);
                confirmed = true;
            }
        }
        return confirmed;
    }

    private void setLastWordType(WordType lastWordType) {
        this.lastWordType = lastWordType;
    }

    private void analyzer(String token, ParserOutput output, ArrayList<Command> commands, ArrayList<GenericObject> oggetti, ArrayList<Personaggio> personaggi) {
        if(lastWordType == WordType.COMANDO) {
            if(!isArticle(token) && !isObject(token, output, oggetti)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.ARTICOLO) {
            if(!isObject(token, output, oggetti) && !isCharacter(token, output, personaggi)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.PREPOSIZIONE) {
            if(!isObject(token, output, oggetti) && !isArticle(token)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.NOME) {
            if(!isPreposition(token, output) && !isAdjective(output, token, oggetti) && !isObject(token, output, oggetti)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.AGGETTIVO) {
            if(!isPreposition(token, output)) {
                accepted = false;
            }
        } else if (lastWordType == WordType.COMANDO_PARLA) {
            if(!isPreposition(token, output) && !isCharacter(token, output, personaggi)){
                accepted = false;
            }
        } else if (lastWordType == WordType.PREPOSIZIONE_PARLA) {
            if(!isCharacter(token, output, personaggi) && !isArticle(token)){
                accepted = false;
            }
        }
    }

    @Override
    public ParserOutput parse(String input, ArrayList<Command> commands, ArrayList<GenericObject> oggetti, ArrayList<Personaggio> personaggi) {
        accepted = false;
        adjectives.clear();
        lastArticle = null;
        lastPreposition = null;
        lastObject = null;
        String cmd = input.toLowerCase().trim();
        String[] tokens = cmd.split("\\s+|'(\\s+)?");
        ParserOutput p = new ParserOutput();
        if(isCommand(tokens[0], p, commands)) {
            accepted = true;
            numberOfObjects = 0;
            for(int i = 1; i < tokens.length; i++) {
                analyzer(tokens[i], p, commands, oggetti, personaggi);
                if(!accepted) {
                    break;
                }
                if(numberOfObjects > 2) {
                    accepted = false;
                    break;
                }
            }
        }
        if(accepted && p.getPrimoOggetto() != null && p.getPrimoAggettivo() == null && p.getPrimoOggetto().getAggettivi() != null) {
        	int numberOfObjects = 0;
        	for(GenericObject oggetto : oggetti) {
        		if(oggetto.getNome().equals(p.getPrimoOggetto().getNome())) {
        			numberOfObjects++;
        			if(numberOfObjects > 1) {
        				accepted = false;
        				break;
        			}
        		}
        	}
        }
        if(accepted && p.getSecondoOggetto() != null && p.getSecondoAggettivo() == null && p.getSecondoOggetto().getAggettivi() != null) {
        	int numberOfObjects = 0;
        	for(GenericObject oggetto : oggetti) {
        		if(oggetto.getNome().equals(p.getSecondoOggetto().getNome())){
        			numberOfObjects++;
        			if(numberOfObjects > 1) {
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
            return p;
        }
    }
}
