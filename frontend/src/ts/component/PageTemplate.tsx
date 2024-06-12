import {Error} from "../general-page/error/Error";
import {FunctionComponent} from "react";
import {CSS_CLASS_WEBSITE_CONTAINER} from "../constant/CSSClassNameConstants";

export const PageTemplate: FunctionComponent<any> = ({Component, props}) => {

    if (props.backendError) {
        return (
            <Error error={props.backendError}/>
        );
    }

    return (
        <div className={CSS_CLASS_WEBSITE_CONTAINER}>
            <Component props={props}/>
        </div>
    );
};