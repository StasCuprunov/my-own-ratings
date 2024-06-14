export const setSiteTitle = (title?: string): void => {
    let suffixTitle: string | undefined = process.env.REACT_APP_SITE_TITLE;

    if (suffixTitle && title) {
        document.title = title + " - " + suffixTitle;
    }
};