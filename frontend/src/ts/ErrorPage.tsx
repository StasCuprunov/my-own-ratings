import {FunctionComponent} from "react";

export const ErrorPage: FunctionComponent<any> = ({error}) => {
    console.log(error);
    let responseInfo: any = error.response;
    let requestInfo: any = error.request;
    if (responseInfo) {
        console.log(responseInfo);
    }
    if (requestInfo) {
        console.log(requestInfo);
    }

    return (
        <div>
            <h1>Something went wrong!</h1>
            <ul>
                <li>This is the error name: {error.name}</li>
                <li>This is the error message: {error.message}</li>
                {responseInfo &&
                    <li>
                        This is the following response:
                        <ul>
                            <li>This is the status code: {responseInfo.status}</li>
                            <li>This are the headers: {responseInfo.headers}</li>
                            <li>This are the data: {responseInfo.data}</li>
                        </ul>
                    </li>
                }
            </ul>
        </div>
    );
}