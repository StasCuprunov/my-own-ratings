import {useNavigate} from "react-router-dom";
import {PasswordRegex} from "./PasswordRegex";
import {ChangeEvent, FunctionComponent, useMemo, useState} from "react";
import {User} from "../model/User";
import {
    createUser,
    getInputEmailProps,
    getInputFirstNameProps,
    getInputPasswordConfirmationProps,
    getInputPasswordProps,
    getInputSurnameProps
} from "./RegistrationFunctions";
import {WEBSITE_ROUTING_INDEX} from "../constant/WebsiteRoutingConstants";
import {RegistrationPage} from "./RegistrationPage";

export const Registration: FunctionComponent<any> = ({props}) => {
    const navigate = useNavigate();
    const maxLengthString: number = props.maximumLengthOfString;

    const passwordRegex: PasswordRegex = useMemo(() => new PasswordRegex(props.atLeastOneDigitRegex,
        props.atLeastOneEnglishUpperCaseLetterRegex, props.atLeastOneEnglishLowerCaseLetterRegex,
        props.atLeastOneValidSpecialCharacterRegex, props.enumerationOfValidSpecialCharacters), []);

    const [user, setUser] = useState(new User());
    const [passwordConfirmation, setPasswordConfirmation] = useState("");

    const [isPasswordValid, setIsPasswordValid] = useState(true);
    const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] =
        useState(true);

    const [passwordErrors, setPasswordErrors] = useState<string[]>([]);
    const [backendError, setBackendError] = useState(null);

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

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        resetPasswordValidation();
        checkPassword();
        checkPasswordConfirmation();
        if (!isPasswordValid || !isPasswordConfirmationValid) {
            return;
        }
        let result: any = await createUser(user);

        const {error} = result;
        if (error) {
            setBackendError(error);
        }
        else {
            navigate(WEBSITE_ROUTING_INDEX);
        }
    };

    const resetPasswordValidation = () => {
        setIsPasswordValid(true);
        setIsPasswordConfirmationValid(true);
    };

    const checkPassword = () => {
        const { isPasswordValid, passwordErrors } = passwordRegex.checkPassword(user.password);
        setIsPasswordValid(isPasswordValid);
        setPasswordErrors(passwordErrors);
    };

    const checkPasswordConfirmation = () => {
        if (passwordConfirmation !== user.password) {
            setIsPasswordConfirmationValid(false);
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

    let propsForPage: any = {backendError, handleSubmit, inputEmail, inputFirstName, inputSurname, inputPassword,
        inputPasswordConfirmation, passwordErrors, isPasswordConfirmationValid};

    return (
        <RegistrationPage props={propsForPage}/>
    );
};