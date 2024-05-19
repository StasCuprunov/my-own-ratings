import {useEffect, useState} from "react";
import {getAxios} from "./BackendCalls";

export const useGet: any = (url: string) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    useEffect(() =>  {
        let load: boolean = true;
        const get = async () => {
            let result = await getAxios(url);
            if (!result) {
                return;
            }
            let data: any = result.data;
            if (data && load) {
                setData(data);
            }
            let error: any = result.error;
            if (error && load) {
                setError(result.error);
            }
        };
        get();
        return () => {
            load = false;
        };
    }, []);
    return {
        data: data,
        error: error
    };
};