import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {Input} from "../../atom/form/input/Input";
import {InputError} from "../../atom/form/input/InputError";

export const FormForInput: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            <Label props={props.label}/>
            <Input props={props.input}/>
            {props.inputError &&
                <InputError props={props.inputError}/>
            }
        </div>
    );
};