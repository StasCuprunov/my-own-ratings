import {FunctionComponent} from "react";

export const Button: FunctionComponent<any> = ({props}) => {
  return (
      <button type="submit">{props.text}</button>
  );
};