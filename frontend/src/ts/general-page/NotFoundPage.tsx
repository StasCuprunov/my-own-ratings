import {FunctionComponent} from "react";
import {CSS_CLASS_CONTAINER, CSS_CLASS_WEBSITE_CONTAINER} from "../constant/CSSClassNameConstants";

export const NotFoundPage: FunctionComponent<any> = () => {
    return (
      <div className={CSS_CLASS_WEBSITE_CONTAINER}>
          <div className={CSS_CLASS_CONTAINER}>
              <h1>Page not found</h1>
              <p>This page does not exist.</p>
          </div>
      </div>
    );
};