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
package org.durid.sql.dialect.mysql.ast.statement;

import org.durid.sql.ast.SQLName;
import org.durid.sql.ast.expr.SQLIdentifierExpr;
import org.durid.sql.ast.expr.SQLPropertyExpr;
import org.durid.sql.dialect.mysql.visitor.MySqlASTVisitor;

public class MySqlShowKeysStatement extends MySqlStatementImpl {

    private static final long serialVersionUID = 1L;

    private SQLName           table;
    private SQLName           database;

    public SQLName getTable() {
        return table;
    }

    public void setTable(SQLName table) {
        if (table instanceof SQLPropertyExpr) {
            SQLPropertyExpr propExpr = (SQLPropertyExpr) table;
            this.setDatabase((SQLName) propExpr.getOwner());
            this.table = new SQLIdentifierExpr(propExpr.getName());
            return;
        }
        this.table = table;
    }

    public SQLName getDatabase() {
        return database;
    }

    public void setDatabase(SQLName database) {
        this.database = database;
    }

    public void accept0(MySqlASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, table);
            acceptChild(visitor, database);
        }
        visitor.endVisit(this);
    }
}
