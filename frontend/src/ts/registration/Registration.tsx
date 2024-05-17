import axios from "axios";
import {FunctionComponent, useEffect, useState} from "react";
import {ROUTING_REGISTRATION} from "../interface/APIConstants";
import {HelloWorld} from "../HelloWorld";
import {ComponentHandling} from "../component/ComponentHandling";

export const Registration: FunctionComponent = () =>  {
    const [registration, setRegistration] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get(ROUTING_REGISTRATION)
            .then((response) => {
                setRegistration(response.data);
            })
            .catch(error => {
                setError(error);
            });
        },[]);

    return (
        <ComponentHandling Component={HelloWorld} error={error}/>
    );
}