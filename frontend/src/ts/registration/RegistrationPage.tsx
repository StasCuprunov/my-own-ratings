import {FunctionComponent} from "react";
import {
    getCreateAccountButtonProps,
    getLoginButtonLink
} from "./RegistrationFunctions";
import {CustomButton} from "../component/atom/button/CustomButton";
import {FormForInput} from "../component/molecule/form-attribute/FormForInput";

const createAccountButton: any = getCreateAccountButtonProps();

export const RegistrationPage: FunctionComponent<any> = ({props}) => {
    return (
        <>
            <form onSubmit={props.handleSubmit}>
                <h1>Registration</h1>
                <FormForInput props={props.formForEmail}/>
                <FormForInput props={props.formForFirstName}/>
                <FormForInput props={props.formForSurname}/>
                <FormForInput props={props.formForPassword}/>
                <FormForInput props={props.formForPasswordConfirmation}/>
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
        </>
    );
};
