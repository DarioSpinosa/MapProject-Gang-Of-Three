/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Parser;
import General.Command;
import General.Name;
import java.util.ArrayList;
import General.GenericObject;
import Entità.Characters.Personaggio;
import General.CommandType;

public class ParserIta extends ParserEssentials {
    
    public ParserIta(ArrayList<Name> articles, ArrayList<String> prepositions) {
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
        }
        return confirmed;
    }
    
    private boolean isArticle(String token) {
        boolean confirmed = false;
        for(Name article : articles) {
            if(article.getName().equals(token)) {
                confirmed = true;
                setLastWordType(article.getType());
                break;
            }
        }
        return confirmed;
    }
    
    private boolean isObject(String token, ParserOutput output, ArrayList<GenericObject> oggetti) {
        boolean confirmed = false;
        for(GenericObject oggetto : oggetti) {
            if(oggetto.getNome().equals(token) || oggetto.confrontaAlias(token)) {
                if((lastWordType == WordType.ARTICOLO_FEMMINILE && oggetto.getTipo() == WordType.NOME_FEMMINILE) ||
                        (lastWordType == WordType.ARTICOLO_MASCHILE && oggetto.getTipo() == WordType.NOME_MASCHILE) ||
                        (lastWordType == WordType.ARTICOLO_APOSTROFO && oggetto.getNome().matches("[aeiou][a-z]+")) ||
                        (lastWordType == WordType.COMANDO) || (lastWordType == WordType.PREPOSIZIONE) || (lastWordType == WordType.NOME_MASCHILE)
                        || (lastWordType == WordType.NOME_FEMMINILE)){
                    confirmed = true;
                    output.setOggetto(oggetto);
                    setObjectQualities(oggetto);
                    setLastWordType(oggetto.getTipo());
                    break;
                }
            }
        }
        return confirmed;
    }
    
    private boolean isCharacter(String token, ParserOutput output, ArrayList<Personaggio> personaggi){
        boolean confirmed = false;
        for(Personaggio personaggio : personaggi){
            if(personaggio.getAlias() != null && personaggio.getAlias().getName().equals(token)){
                if((lastWordType == WordType.ARTICOLO_FEMMINILE && personaggio.getAlias().getType() == WordType.NOME_FEMMINILE) ||
                        (lastWordType == WordType.ARTICOLO_MASCHILE && personaggio.getAlias().getType() == WordType.NOME_MASCHILE) ||
                        (lastWordType == WordType.ARTICOLO_APOSTROFO && personaggio.getAlias().getName().matches("[aeiou][a-z]+")) ||
                        (lastWordType == WordType.COMANDO) || (lastWordType == WordType.PREPOSIZIONE)
                        || (lastWordType == WordType.COMANDO_PARLA) || (lastWordType == WordType.PREPOSIZIONE_PARLA)){
                    confirmed = true;
                    output.setPersonaggio(personaggio);
                    setLastWordType(WordType.NOME_PROPRIO);
                    break;
                }
                } else if (personaggio.getNome().equals(token)){
                    if(lastWordType == WordType.COMANDO || lastWordType == WordType.PREPOSIZIONE
                            || lastWordType == WordType.COMANDO_PARLA || lastWordType == WordType.PREPOSIZIONE_PARLA){
                        confirmed = true;
                        output.setPersonaggio(personaggio);
                        setLastWordType(WordType.NOME_PROPRIO);
                        break;
                }
            }
        }
        return confirmed;
    }
    
    private void setObjectQualities(GenericObject oggetto) {
        numberOfObjects++;
        if(oggetto.getAggettivi()!=null && !oggetto.getAggettivi().isEmpty()) {
            adjectives.clear();
            adjectives.addAll(oggetto.getAggettivi());
        }
    }
    
    private boolean isAdjective(String token) {
        boolean confirmed = false;
        if(adjectives.contains(token)) {
            confirmed = true;
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
        } else if(lastWordType == WordType.ARTICOLO_FEMMINILE || lastWordType == WordType.ARTICOLO_MASCHILE || lastWordType == WordType.ARTICOLO_APOSTROFO) {
            if(!isObject(token, output, oggetti) && !isCharacter(token, output, personaggi)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.PREPOSIZIONE) {
            if(!isObject(token, output, oggetti) && !isArticle(token)) {
                accepted = false;
            }
        } else if(lastWordType == WordType.NOME_MASCHILE || lastWordType == WordType.NOME_FEMMINILE) {
            if(!isPreposition(token, output) && !isAdjective(token) && !isObject(token, output, oggetti)) {
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
        if(lastWordType == WordType.ARTICOLO_FEMMINILE || lastWordType == WordType.ARTICOLO_MASCHILE || lastWordType == WordType.ARTICOLO_APOSTROFO || lastWordType == WordType.PREPOSIZIONE) {
            accepted = false;
        }
        if(!accepted) {
            return null;
        } else {
            return p;
        }
    }
}
