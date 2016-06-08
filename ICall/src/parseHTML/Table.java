/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseHTML;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator.Tag;

/**
 *
 * @author Aleks
 */
public class Table {
    private String content;
    private int index;
    private Element cont;

    public Table(String content, int index) {
        this.content = content;
        this.index = index;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
