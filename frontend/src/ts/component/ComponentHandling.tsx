import React, {FunctionComponent} from "react";
import {ErrorPage} from "../general-page/ErrorPage";
import {WebsiteLoadingPage} from "../general-page/WebsiteLoadingPage";

export const ComponentHandling: FunctionComponent<any> = ({HtmlComponent, props, error}) =>  {
    if (error) {
        return (
          <ErrorPage error={error}/>
        );
    }
    else if (props) {
        return (
          <HtmlComponent props={props}/>
        );
    }
    return (
        <WebsiteLoadingPage/>
    );
}