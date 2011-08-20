package org.albianj.persistence.impl.toolkit;

import org.albianj.persistence.impl.context.*;

public class EnumMapping
{
	 public static String GetRelationalOperators(RelationalOperator opt)
     {
         switch (opt)
         {
             case And:
                 {
                     return "AND";
                 }
             case OR:
                 {
                     return "OR";
                 }
             default:
                 {
                     return "AND";
                 }
         }
     }

     public static String GetLogicalOperation(LogicalOperation opt)
     {
         switch (opt)
         {
             case Equal:
                 {
                     return "=";
                 }
             case Greater:
                 {
                     return ">";
                 }
             case GreaterOrEqual:
                 {
                     return ">=";
                 }
             case Is:
                 {
                     return "IS";
                 }
             case Less:
                 {
                     return "<";
                 }
             case LessOrEqual:
                 {
                     return "<=";
                 }
             case NotEqual:
                 {
                     return "<>";
                 }
             default:
                 {
                     return "=";
                 }
         }
     }

}
