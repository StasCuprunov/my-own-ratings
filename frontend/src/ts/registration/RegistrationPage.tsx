import {FunctionComponent} from "react";
import {Label} from "../component/atom/form/Label";
import {Input} from "../component/atom/form/Input";
import {
    getCreateAccountButtonProps,
    getInputErrorPasswordConfirmationProps,
    getInputErrorPasswordProps,
    getLabelEmailProps,
    getLabelFirstNameProps,
    getLabelPasswordConfirmationProps,
    getLabelPasswordProps,
    getLabelSurnameProps
} from "./RegistrationFunctions";
import {Button} from "../component/atom/button/Button";
import {InputError} from "../component/atom/form/InputError";
import {ErrorPage} from "../general-page/ErrorPage";

const labelEmail: any = getLabelEmailProps();
const labelFirstName: any = getLabelFirstNameProps();
const labelSurname: any = getLabelSurnameProps();
const labelPassword: any = getLabelPasswordProps();
const labelPasswordConfirmation: any = getLabelPasswordConfirmationProps();

const createAccountButton: any = getCreateAccountButtonProps();

export const RegistrationPage: FunctionComponent<any> = ({props}) => {

    if (props.backendError) {
        return (
          <ErrorPage error={props.backendError}/>
        );
    }

    return (
        <div>
            <form onSubmit={props.handleSubmit}>
                <h1>Registration</h1>
                <div>
                    <Label props={labelEmail}/>
                    <Input props={props.inputEmail}/>
                </div>
                <div>
                    <Label props={labelFirstName}/>
                    <Input props={props.inputFirstName}/>
                </div>
                <div>
                    <Label props={labelSurname}/>
                    <Input props={props.inputSurname}/>
                </div>
                <div>
                    <Label props={labelPassword}/>
                    <Input props={props.inputPassword}/>
                    <InputError props={getInputErrorPasswordProps(!props.isPasswordValid, props.passwordErrors)}/>
                </div>
                <div>
                    <Label props={labelPasswordConfirmation}/>
                    <Input props={props.inputPasswordConfirmation}/>
                    <InputError props={getInputErrorPasswordConfirmationProps(!props.isPasswordConfirmationValid)}/>
                </div>
                <div>
                    <Button props={createAccountButton}/>
                </div>
                <div>
                    <h2>Hints</h2>
                    <ul>
                        <li>
                            <sup>1</sup>Required
                        </li>
                        <li>
                            <sup>2</sup>
                            The password must contain minimum one digit, one uppercase English letter, one lowercase
                            English letter, and one of the following special
                            characters: {props.enumerationOfValidSpecialCharacters}
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
};
