import {FunctionComponent} from "react";
import {Label} from "../component/atom/form/Label";
import {Input} from "../component/atom/form/input/Input";
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
import {InputError} from "../component/atom/form/input/InputError";
import {ErrorPage} from "../general-page/ErrorPage";

const labelEmail: any = getLabelEmailProps();
const labelFirstName: any = getLabelFirstNameProps();
const labelSurname: any = getLabelSurnameProps();
const labelPassword: any = getLabelPasswordProps();
const labelPasswordConfirmation: any = getLabelPasswordConfirmationProps();

const createAccountButton: any = getCreateAccountButtonProps();

export const RegistrationPage: FunctionComponent<any> = ({backendError, handleSubmit, inputEmail, inputFirstName,
                                                             inputSurname, inputPassword, isPasswordValid, passwordErrorText, inputPasswordConfirmation, isPasswordConfirmationValid}) => {
    if (backendError) {
        return (
          <ErrorPage error={backendError}/>
        );
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Registration</h1>
                <div>
                    <Label props={labelEmail}/>
                    <Input props={inputEmail}/>
                </div>
                <div>
                    <Label props={labelFirstName}/>
                    <Input props={inputFirstName}/>
                </div>
                <div>
                    <Label props={labelSurname}/>
                    <Input props={inputSurname}/>
                </div>
                <div>
                    <Label props={labelPassword}/>
                    <Input props={inputPassword}/>
                    <InputError props={getInputErrorPasswordProps(!isPasswordValid, passwordErrorText)}/>
                </div>
                <div>
                    <Label props={labelPasswordConfirmation}/>
                    <Input props={inputPasswordConfirmation}/>
                    <InputError props={getInputErrorPasswordConfirmationProps(!isPasswordConfirmationValid)}/>
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
                    </ul>
                </div>
            </form>
        </div>
    );
};
