package xyz.iridiumion.enlightened.highlightingdefinitions.definitions

import java.util.regex.Pattern

import xyz.iridiumion.enlightened.editor.HighlightingDefinition

/**
 * Author: 0xFireball, IridiumIon Software
 */
class NoHighlightingDefinition : HighlightingDefinition {


    override fun getLinePattern(): Pattern {
        return PATTERN_LINE
    }

    override fun getNumberPattern(): Pattern {
        return PATTERN_NUMBERS
    }

    override fun getPreprocessorPattern(): Pattern {
        return PATTERN_PREPROCESSOR
    }

    override fun getKeywordPattern(): Pattern {
        return PATTERN_KEYWORDS
    }

    override fun getBuiltinsPattern(): Pattern {
        return PATTERN_BUILTINS
    }

    override fun getCommentsPattern(): Pattern {
        return PATTERN_COMMENTS
    }

    override fun getStringPattern(): Pattern {
        return PATTERN_STRING
    }

    override fun getSymbolPattern(): Pattern {
        return PATTERN_SYMBOL
    }

    override fun getIdentifierPattern(): Pattern {
        return PATTERN_IDENTIFIER
    }

    companion object {
        //Default Highlighting definitions
        private val PATTERN_LINE = Pattern.compile(".*\\n")
        private val PATTERN_NUMBERS = Pattern.compile("\\b(\\d*[.]?\\d+)\\b")
        private val PATTERN_PREPROCESSOR = Pattern.compile("a^")
        private val PATTERN_STRING = Pattern.compile("a^")
        private val PATTERN_KEYWORDS = Pattern.compile("a^")
        private val PATTERN_BUILTINS = Pattern.compile("a^")
        private val PATTERN_COMMENTS = Pattern.compile("a^")
        private val PATTERN_SYMBOL = Pattern.compile("a^")
        private val PATTERN_IDENTIFIER = Pattern.compile("a^")
    }

}
