import {FunctionComponent} from "react";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {CSS_CLASS_CONTAINER} from "../../constant/CSSClassNameConstants";

export const ErrorPage: FunctionComponent<any> = ({props}) => {
    return (
        <div className={CSS_CLASS_CONTAINER}>
            <h1>{props.message}</h1>
            <div className={CSS_CLASS_CONTAINER}>
                <h2>Description</h2>
                <p>{props.description}</p>
            </div>
            <CustomButton props={props.reloadButtonProps}/>
        </div>
    );
};