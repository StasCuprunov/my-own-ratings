import React, {FunctionComponent} from "react";
import {ErrorPage} from "../general-page/ErrorPage";
import {WebsiteLoadingPage} from "../general-page/WebsiteLoadingPage";

export const LoadComponentHandling: FunctionComponent<any> = ({Component, props, error}) =>  {
    if (error) {
        return (
          <ErrorPage error={error}/>
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