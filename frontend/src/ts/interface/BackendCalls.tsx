import axios from "axios";

export const getAxios = async (url: string) => {
    let data: any = null;
    let errorResult: any = null;
    await axios.get(url)
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
  await axios.post(url, input)
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