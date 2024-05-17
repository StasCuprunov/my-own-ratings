import {useEffect, useState} from "react";
import axios from "axios";

export const useGet: any = (url: string) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    useEffect(() =>  {
        let load: boolean = true;
        const get = async () => {
            await axios.get(url)
                .then((response) => {
                    if (load) {
                        setData(response.data);
                    }
                })
                .catch(error => {
                    if (load) {
                        setError(error);
                    }
                });
        };
        get();
        return () => {
            load = false;
        };
    }, []);
    return {
        data: data,
        error: error
    }
};