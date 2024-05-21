import {FunctionComponent} from "react";

export const ErrorPage: FunctionComponent<any> = ({error}) => {
    console.log(error);

    let response: any = error.response;
    let isNetworkError: any = error.message === "Network Error";
    return (
        <div>
            <h1>{error.message}</h1>
            <ul>
                {isNetworkError &&
                    <li>The backend server is probably not available.</li>
                }
                {response &&
                    <li>{response.data}</li>
                }
            </ul>
        </div>
    );
};