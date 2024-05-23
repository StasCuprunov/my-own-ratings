import axios, {AxiosInstance} from "axios";

const api: AxiosInstance = axios.create({
    withCredentials: true
});

export const getAxios = async (url: string) => {
    let data: any = null;
    let errorResult: any = null;
    await api.get(url)
        .then((response) => {
            data = response.data;
        })
        .catch(error => {
            errorResult = error;
        });
    return {
        data: data,
        error: errorResult
    }
};

export const postAxios = async (url: string, input: any) => {
  let data: any = null;
  let errorResult: any = null;
  await api.post(url, input)
      .then((response) => {
          data = response.data;
      })
      .catch(error => {
          errorResult = error;
      });
    return {
        data: data,
        error: errorResult
    }
};