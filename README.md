# symphony-opennlp-java
Library for integration with openNLP for Symphony bots
Given a message and a file with a list of patterns, returns a list of actions and any given parameters found.


### Install using maven
        <dependency>
            <groupId>com.symphony.platformsolutions</groupId>
            <artifactId>symphony-opennlp-java</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        
## Configuration
Create a nlp-config.json file in your project which includes the following properties

    {
      "posmodelPath": "full path to OpenNLP POS model",
      "sentenceSplitterModelPath": "full path to OpenNLP Sentence model",
      "patternsFile": "full path to your patterns.json file"
    }
    
Create a patterns.json file. It defines a list of patterns to be mapped into actions. For example:
    [
    
      {"action":"buyShares",
        "posrequirementList":[
          {"options":["buy","purchase"],"parameter":null,"pos":"V"},
          {"options":null,"parameter":"quantity","pos":"CD"},
          {"options":["shares","stock"],"parameter":null,"pos":"NN"},
          {"options":null,"parameter":null,"pos":"IN"},
          {"options":null,"parameter":"product","pos":"NN"}]
      },
      {"action":"buyShares",
        "posrequirementList":[
          {"options":["buy","purchase"],"parameter":null,"pos":"V"},
          {"options":null,"parameter":"product","pos":"NN"},
          {"options":null,"parameter":"quantity","pos":"CD"}]
      },
      {"action":"buyShares",
        "posrequirementList":[
          {"options":["buy","purchase"],"parameter":null,"pos":"V"},
          {"options":null,"parameter":"product","pos":"*"},
          {"options":null,"parameter":"quantity","pos":"CD"}]
      },
      {"action":"buyShares",
        "posrequirementList":[
          {"options":["buy","purchase"],"parameter":null,"pos":"V"},
          {"options":null,"parameter":"quantity","pos":"CD"},
          {"options":null,"parameter":"product","pos":"NN"}]
      },
      {"action": "decision",
      "posrequirementList":[{"pos":"UH","options":["yes","yeah","sure","yep","confirm","y"],"parameter":"decisionBool", "value": "true"}]
      },
      {"action": "decision",
        "posrequirementList":[{"pos":"UH","options":["no","nope","reject","n"],"parameter":"decisionBool", "value": "false"}]
      },
      {"action":"headlines",
        "posrequirementList":[
          {"options":["headlines","news","articles"],"parameter":null,"pos":"NN"},
          {"options":null,"parameter":null,"pos":"*"},
          {"options":null,"parameter":"product","pos":"NN"}]
      },
      {"action":"headlines",
        "posrequirementList":[
          {"options":null,"parameter":"product","pos":"NN"},
          {"options":["headlines","news","articles"],"parameter":null,"pos":"NN"}
          ]
      }
    ]

For each Pattern object:

* action is the name of the action to be mapped to, many patterns can match the same action
* posrequirementList is the list of Parts of speech requiements for the pattern
    - options (optional) defines a specific list of words that the POS needs to match to fit the pattern
    - paramater  (optional) defines the name of the pafamater that the value of this POS would fill
    - pos (required) defines the POS to match for, or * for any POS in that position For a list of POS click [here](https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html)

## How to use in your bot code:
    URL nlpUrl = getClass().getResource("nlp-config.json");
    NLPConfig nlpConfig = NLPConfigLoader.loadFromFile(nlpUrl.getPath());
    NLPService nlp = new NLPService(nlpConfig);
    List<Action> actions = nlp.match(inboundMessage.getMessageText());
