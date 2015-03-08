/*
 * *
 *
 *
 * Copyright 2015 Bill Bejeck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package bbejeck.nosql.lucene;

import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.queries.FilterClause;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Bill Bejeck
 * Date: 2/18/15
 * Time: 10:38 PM
 */
public class QueryParseResults {

    private String indexPath;
    private List<String> selectFields = new ArrayList<>();
    private BooleanQuery booleanQuery;
    private BooleanFilter booleanFilter;



    private QueryParseResults(Builder builder) {
        indexPath = builder.indexPath;
        selectFields = builder.selectFields;
        booleanQuery = builder.booleanQuery;
        booleanFilter = builder.booleanFilter;
    }

    public String getIndexPath() {
        return indexPath;
    }

    public List<String> getSelectFields() {
        return selectFields;
    }

    public BooleanQuery getBooleanQuery() {
        return booleanQuery;
    }

    public BooleanFilter getBooleanFilter() {
        return booleanFilter;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(QueryParseResults copy) {
        Builder builder = new Builder();
        builder.indexPath = copy.indexPath;
        builder.selectFields = copy.selectFields;
        builder.booleanQuery = copy.booleanQuery;
        builder.booleanFilter = copy.booleanFilter;
        return builder;
    }


    public static final class Builder implements LuceneQueryFunctions {
        private String indexPath;
        private List<String> selectFields;
        private List<FilterClause> filterClausedList;
        private BooleanQuery booleanQuery;
        private BooleanFilter booleanFilter;

        private Builder() {
        }

        public Builder withIndexPath(String indexPath) {
            this.indexPath = indexPath;
            return this;
        }

        public Builder withSelectFields(List<String> selectFields) {
            this.selectFields = selectFields;
            return this;
        }

        public QueryParseResults build() {
            return new QueryParseResults(this);
        }

        public Builder withFilterClausesList(List<FilterClause> filterClausesList) {
            this.filterClausedList = filterClausesList;
            return this;
        }

        public Builder withBooleanClausesList(List<BooleanClause> booleanClausesList) {
            this.booleanQuery = toBooleanQuery.apply(booleanClausesList);
            return this;
        }
    }
}
