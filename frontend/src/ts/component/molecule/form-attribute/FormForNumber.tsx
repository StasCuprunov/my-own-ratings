import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {InputNumber} from "../../atom/form/input/InputNumber";
import {InputError} from "../../atom/form/input/InputError";
import {CSS_CLASS_FORM_ATTRIBUTE} from "../../../constant/CSSClassNameConstants";

export const FormForNumber: FunctionComponent<any> = ({props}) => {
    return (
      <div className={CSS_CLASS_FORM_ATTRIBUTE}>
          <Label props={props.label}/>
          <InputNumber props={props.inputNumber}/>
          {props.inputError &&
              <InputError props={props.inputError}/>
          }
      </div>
    );
};