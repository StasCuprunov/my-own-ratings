import {ChangeEvent, FunctionComponent, useMemo, useState} from "react";
import {Label} from "../component/atom/form/Label";
import {Input} from "../component/atom/form/Input";
import {
    getCreateAccountButtonProps,
    getInputEmailProps,
    getInputErrorPasswordConfirmationProps,
    getInputErrorPasswordProps,
    getInputFirstNameProps,
    getInputPasswordConfirmationProps,
    getInputPasswordProps,
    getInputSurnameProps,
    getLabelEmailProps,
    getLabelFirstNameProps,
    getLabelPasswordConfirmationProps,
    getLabelPasswordProps,
    getLabelSurnameProps
} from "./RegistrationFunctions";
import {PasswordRegex} from "./PasswordRegex";
import {Button} from "../component/atom/button/Button";
import {InputError} from "../component/atom/form/InputError";

const labelEmail: any = getLabelEmailProps();
const labelFirstName: any = getLabelFirstNameProps();
const labelSurname: any = getLabelSurnameProps();
const labelPassword: any = getLabelPasswordProps();
const labelPasswordConfirmation: any = getLabelPasswordConfirmationProps();

const createAccountButton: any = getCreateAccountButtonProps();

export const RegistrationPage: FunctionComponent<any> = ({props}) => {
    const maxLengthString: number = props.maximumLengthOfString;

    const passwordRegex: PasswordRegex = useMemo(() => new PasswordRegex(props.atLeastOneDigitRegex,
        props.atLeastOneEnglishUpperCaseLetterRegex, props.atLeastOneEnglishLowerCaseLetterRegex,
        props.atLeastOneValidSpecialCharacterRegex, props.enumerationOfValidSpecialCharacters), []);

    const [user, setUser] = useState({
        id: "",
        email: "",
        firstName: "",
        surname: "",
        password: ""
    });
    const [passwordConfirmation, setPasswordConfirmation] = useState("");

    const [isPasswordValid, setIsPasswordValid] = useState(true);
    const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] =
        useState(true);

    const [passwordErrors, setPasswordErrors] = useState<string[]>([]);

    const handleUserChange = (field: string, setUser: any) => {
        return (e: ChangeEvent<HTMLInputElement>) => {
            setUser((prev: any) => ({
                ...prev,
                [field]: e.target.value
            }));
        };
    };

    const handlePasswordConfirmationChange = (event: any) => {
        setPasswordConfirmation(event.target.value);
    };

    const resetPasswordValidation = () => {
        setIsPasswordValid(true);
        setIsPasswordConfirmationValid(true);
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();
        resetPasswordValidation();

        const { isPasswordValid, passwordErrors } = passwordRegex.checkPassword(user.password);
        setIsPasswordValid(isPasswordValid);
        setPasswordErrors(passwordErrors);

        if (passwordConfirmation !== user.password) {
            setIsPasswordConfirmationValid(false);
        }
        if (!isPasswordValid || !isPasswordConfirmationValid) {
            return;
        }
    };

    const inputEmail: any = useMemo(() =>
            getInputEmailProps(user.email, maxLengthString, handleUserChange("email", setUser)),
        [user.email]
    );
    let inputFirstName: any = useMemo( () =>
        getInputFirstNameProps(user.firstName, maxLengthString, handleUserChange("firstName", setUser)),
        [user.firstName]
    );
    let inputSurname: any = useMemo(() =>
        getInputSurnameProps(user.surname, maxLengthString, handleUserChange("surname", setUser)),
        [user.surname]
    );
    let inputPassword: any = useMemo(() =>
        getInputPasswordProps(user.password, props.passwordMinimumLength, props.passwordMaximumLength,
            handleUserChange("password", setUser)),
        [user.password]
    );
    let inputPasswordConfirmation: any = useMemo(() =>
        getInputPasswordConfirmationProps(passwordConfirmation, handlePasswordConfirmationChange),
        [passwordConfirmation]
    );

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
                    <InputError props={getInputErrorPasswordProps(!isPasswordValid, passwordErrors)}/>
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
}
