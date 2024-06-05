import {FunctionComponent} from "react";
import {CSS_CLASS_WEBSITE_CONTAINER} from "../constant/CSSClassNameConstants";

export const WebsiteLoadingPage: FunctionComponent = () =>  {
  return (
      <div className={CSS_CLASS_WEBSITE_CONTAINER}>
          <h1>Website is loading...</h1>
      </div>
  );
};