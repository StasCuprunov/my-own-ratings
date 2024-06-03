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
    getLabelSurnameProps,
    getLoginButtonLink
} from "./RegistrationFunctions";
import {CustomButton} from "../component/atom/button/CustomButton";
import {InputError} from "../component/atom/form/input/InputError";
import {Error} from "../general-page/error/Error";

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
            <Error error={backendError}/>
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
                    <CustomButton props={createAccountButton}/>
                </div>
                <div>
                    <h2>Are you already registered?</h2>
                    {getLoginButtonLink()}
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
