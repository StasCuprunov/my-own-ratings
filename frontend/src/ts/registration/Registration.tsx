import {useRegistration} from "./RegistrationFunctions";
import {RegistrationPage} from "./RegistrationPage";
import {ComponentHandling} from "../component/ComponentHandling";
import {FunctionComponent} from "react";

export const Registration: FunctionComponent<any> = () =>  {
    let {data, error} = useRegistration();

    return (
        <ComponentHandling error={error} HtmlComponent={RegistrationPage} props={data}/>
    );
};