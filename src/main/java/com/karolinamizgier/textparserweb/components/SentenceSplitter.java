package com.karolinamizgier.textparserweb.components;

import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("sentenceSplitter")
public class SentenceSplitter implements Splitter {
    static final TokenizerFactory TOKENIZER_FACTORY
            = IndoEuropeanTokenizerFactory.INSTANCE;
    static final SentenceModel SENTENCE_MODEL
            = new IndoEuropeanSentenceModel();

    @Override
    public List<String> split(String text){
        List<String> tokenList = new ArrayList<String>();
        List<String> whiteList = new ArrayList<String>();
        List<String> converted = new ArrayList<String>();
        Tokenizer tokenizer
                = TOKENIZER_FACTORY.tokenizer(text.toCharArray(),
                0,text.length());
        tokenizer.tokenize(tokenList,whiteList);
        String[] tokens = new String[tokenList.size()];
        String[] whites = new String[whiteList.size()];
        tokenList.toArray(tokens);
        whiteList.toArray(whites);

        int[] sentenceBoundaries
                = SENTENCE_MODEL.boundaryIndices(tokens,whites);
        int sentStartTok = 0;
        int sentEndTok = 0;
        for (int i = 0; i < sentenceBoundaries.length; ++i) {
            sentEndTok = sentenceBoundaries[i];
            StringBuilder conversion = new StringBuilder();

            for (int j=sentStartTok; j <= sentEndTok; j++) {
                conversion.append(tokens[j]+whites[j+1]);
            }
            converted.add(conversion.toString());
            sentStartTok = sentEndTok+1;
        }

        return converted;
    }
}