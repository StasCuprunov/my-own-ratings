import React, {FunctionComponent} from "react";
import {Error} from "../general-page/error/Error";
import {WebsiteLoadingPage} from "../general-page/WebsiteLoadingPage";

export const LoadComponentHandling: FunctionComponent<any> = ({Component, props, error}) =>  {
    if (error) {
        return (
            <Error error={error}/>
        );
    }
    else if (props) {
        return (
            <Component props={props}/>
        );
    }
    return (
        <WebsiteLoadingPage/>
    );
}