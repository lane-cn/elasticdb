/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.durid.sql.ast.expr;

import org.durid.sql.ast.SQLExprImpl;
import org.durid.sql.ast.statement.SQLSelect;
import org.durid.sql.visitor.SQLASTVisitor;

public class SQLAnyExpr extends SQLExprImpl {

    private static final long serialVersionUID = 1L;
    public SQLSelect          subQuery;

    public SQLAnyExpr(){

    }

    public SQLAnyExpr(SQLSelect select){

        this.subQuery = select;
    }

    public SQLSelect getSubQuery() {
        return this.subQuery;
    }

    public void setSubQuery(SQLSelect subQuery) {
        this.subQuery = subQuery;
    }

    public void output(StringBuffer buf) {
        this.subQuery.output(buf);
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.subQuery);
        }

        visitor.endVisit(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((subQuery == null) ? 0 : subQuery.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SQLAnyExpr other = (SQLAnyExpr) obj;
        if (subQuery == null) {
            if (other.subQuery != null) {
                return false;
            }
        } else if (!subQuery.equals(other.subQuery)) {
            return false;
        }
        return true;
    }
}
