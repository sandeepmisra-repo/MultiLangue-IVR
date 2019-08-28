package english.phrasesets;

/**
 * This phraseset class will be automatically populated with data from the 
 * phraseset project file.  Manual edits should only be outside of the tag 
 * areas or overridden methods. 
 * Class created on: 
 * Last generated by Orchestration Designer at: 2019-JUL-03  05:48:31 PM
 */
public class OutTTS_Eng extends com.avaya.sce.runtime.Phraseset { 

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

		// phrase: YouHaveEntered_Eng
		com.avaya.sce.runtime.Phraseset.addLocalPhrase(PHRASE_MAP, "YouHaveEntered_Eng", "YouHaveEntered_Eng.wav", ""); 

		// phrase: Confrim_Eng
		com.avaya.sce.runtime.Phraseset.addLocalPhrase(PHRASE_MAP, "Confrim_Eng", "Confirm_Eng.wav", ""); 

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
	 * Last generated by Orchestration Designer at: 2019-AUG-28  10:11:09 AM
	 * @see com.avaya.sce.runtime.Phraseset#getPhrase(String) 
	 */ 
	public com.avaya.sce.runtime.Phrase getPhrase(String phraseName) {
		return(getPhrase(OutTTS_Eng.PHRASE_MAP, phraseName));
	}
}
