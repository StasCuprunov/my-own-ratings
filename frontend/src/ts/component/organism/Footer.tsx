import {FunctionComponent} from "react";
import {CSS_CLASS_TITLE} from "../../constant/CSSClassNameConstants";

export const Footer: FunctionComponent<any> = () => {
    const contact: string = "stas.cuprunov@t-online.de";
    return (
        <footer>
            <div>
                <div className={CSS_CLASS_TITLE}>Contact information</div>
                <ul>
                    <li>
                        Email: <a href={"mailto:" + contact}>{contact}</a>
                    </li>
                </ul>
            </div>
        </footer>
    );
};