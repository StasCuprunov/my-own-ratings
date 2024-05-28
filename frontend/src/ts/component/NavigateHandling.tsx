import React, {FunctionComponent} from "react";
import {Navigate} from "react-router-dom";
import {Error} from "../general-page/error/Error";
import {WebsiteLoadingPage} from "../general-page/WebsiteLoadingPage";

export const NavigateHandling: FunctionComponent<any> = ({link, data, error}) =>  {
    if (error) {
        return (
            <Error error={error}/>
        );
    }
    else if (data) {
        return (
            <Navigate to={link}/>
        );
    }
    return (
        <WebsiteLoadingPage/>
    );
}