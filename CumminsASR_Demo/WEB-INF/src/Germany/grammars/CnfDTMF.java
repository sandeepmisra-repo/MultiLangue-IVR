package Germany.grammars;

/**
 * This class is generated automatically.
 * Only exit this class for Dynamic External grammars and override the method String getURL().
 * Last generated by Orchestration Designer at: 2019-JUL-03  05:24:51 PM
 */
public class CnfDTMF extends com.avaya.sce.runtime.Grammar {

    //{{START:CLASS:FIELDS
    //}}END:CLASS:FIELDS

    /**
    * Constructor for CnfDTMF.
    */
    public CnfDTMF() {
		//{{START:CLASS:CONSTRUCTOR
        super();
        setRootLanguage("hi-in");
        setMode("dtmf");
        setBuiltin(true);
        setBuiltinMode("dtmf");
        setType("digits");
        setOptions("length=1");
		//}}END:CLASS:CONSTRUCTOR
    }

}

