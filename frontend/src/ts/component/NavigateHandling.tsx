import React, {FunctionComponent} from "react";
import { Navigate } from "react-router-dom";
import {ErrorPage} from "../general-page/ErrorPage";
import {WebsiteLoadingPage} from "../general-page/WebsiteLoadingPage";

export const NavigateHandling: FunctionComponent<any> = ({link, data, error}) =>  {
    if (error) {
        return (
            <ErrorPage error={error}/>
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