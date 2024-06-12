import {FunctionComponent} from "react";
import {Link} from "react-router-dom";
import {CSS_CLASS_LINK_WITH_TEXT} from "../../constant/CSSClassNameConstants";

export const LinkWithText: FunctionComponent<any> = ({props}) => {
    return (
        <div className={CSS_CLASS_LINK_WITH_TEXT}>
            <span>{props.textBefore}</span>
            <Link to={props.to}>{props.textLink}</Link>
        </div>
    );
};