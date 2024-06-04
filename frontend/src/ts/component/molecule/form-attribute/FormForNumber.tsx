import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {InputNumber} from "../../atom/form/input/InputNumber";
import {InputError} from "../../atom/form/input/InputError";

export const FormForNumber: FunctionComponent<any> = ({props}) => {
    return (
      <div>
          <Label props={props.label}/>
          <InputNumber props={props.inputNumber}/>
          {props.inputError &&
              <InputError props={props.inputError}/>
          }
      </div>
    );
};