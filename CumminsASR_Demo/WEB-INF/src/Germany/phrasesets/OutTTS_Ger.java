package Germany.phrasesets;

/**
 * This phraseset class will be automatically populated with data from the 
 * phraseset project file.  Manual edits should only be outside of the tag 
 * areas or overridden methods. 
 * Class created on: 
 * Last generated by Orchestration Designer at: 2019-JUL-03  05:29:40 PM
 */
public class OutTTS_Ger extends com.avaya.sce.runtime.Phraseset { 

	//{{START:CLASS:FIELDS
	private static final com.avaya.sce.runtime.Phraseset.PhraseMap PHRASE_MAP;
	//}}END:CLASS:FIELDS

	/**
	 * Static initializer
	 */
	static {
		//{{START:PHRASESET:STATIC

		// Create the phrase map, then populate with phrases
		PHRASE_MAP = new com.avaya.sce.runtime.Phraseset.PhraseMap(4);
		com.avaya.sce.runtime.Phrase phrase;

		// phrase: YouHaveEntered_Ger
		com.avaya.sce.runtime.Phraseset.addLocalPhrase(PHRASE_MAP, "YouHaveEntered_Ger", "YouHaveEntered_Ger.wav", ""); 

		// phrase: Confirm_Ger
		com.avaya.sce.runtime.Phraseset.addLocalPhrase(PHRASE_MAP, "Confirm_Ger", "Confirm_Ger.wav", ""); 

		//}}END:PHRASESET:STATIC
	}
	/**
	 * Returns the Phrase with the given name.  To override the
	 * behavior and dynamically return a different Phrase object,
	 * override the method "hookGetPhrase(...)".
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2019-AUG-28  10:11:02 AM
	 * @see com.avaya.sce.runtime.Phraseset#getPhrase(String) 
	 */ 
	public com.avaya.sce.runtime.Phrase getPhrase(String phraseName) {
		return(getPhrase(OutTTS_Ger.PHRASE_MAP, phraseName));
	}
}
