package com.devapp.to_docompose.util

enum class Action {
    INSERT,
    UPDATE,
    DELETE,
    UNDO,
    DELETE_ALL,
    NO_ACTION,
}
fun String?.toAction():Action{
    return when{
        this =="INSERT"->{
            Action.INSERT
        }
        this =="UPDATE"->{
            Action.UPDATE
        }
        this =="DELETE"->{
            Action.DELETE
        }
        this =="DELETE_ALL"->{
            Action.DELETE_ALL
        }
        this =="UNDO"->{
            Action.UNDO
        }
        else ->Action.NO_ACTION
    }
}