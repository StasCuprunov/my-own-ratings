export {};

const configuration = require("./gulp_configuration.json");
const minimizingCssConfig = configuration.minimizingCssConfig;

const gulp = require("gulp");
const watch = require("gulp-watch");
const concat = require("gulp-concat");
const cleanCss = require("gulp-clean-css");
const sass = require("gulp-sass")(require("sass"));

const GULP_TASK_WATCH = "watch";
const GULP_TASK_MINIMIZING_CSS = "minimizing-css";

gulp.task(GULP_TASK_MINIMIZING_CSS, function () {
    return gulp.src(minimizingCssConfig.srcDirectories)
        .pipe(sass().on("error", sass.logError))
        .pipe(concat(minimizingCssConfig.minimizedFileName))
        .pipe(cleanCss())
        .pipe(gulp.dest(minimizingCssConfig.destDirectory));
});

gulp.task(GULP_TASK_WATCH, function() {
    return watch(minimizingCssConfig.srcDirectories, gulp.series([GULP_TASK_MINIMIZING_CSS]))
});

gulp.task("default",
    gulp.series(
        GULP_TASK_MINIMIZING_CSS,
        GULP_TASK_WATCH
    )
);
