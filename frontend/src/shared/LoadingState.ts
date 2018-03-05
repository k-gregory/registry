export interface LoadingStateBase<T> {
    failed: boolean;
    loading: boolean;
    present: boolean;
    data?: T;
}

export interface LoadingError<T> extends LoadingStateBase<T> {
    kind: 'error';

    failed: true;
    loading: false;
    present: false;

    error: string;
    message: string;
}

export interface LoadingProgress<T> extends LoadingStateBase<T> {
    kind: 'progress';

    failed: false;
    loading: true;
    present: false;
}

export interface LoadedData<T> extends LoadingStateBase<T> {
    kind: 'loaded';

    failed: false;
    loading: false;
    present: true;
    data: T;
}

export function loadingError<T>(error: string, message: string): LoadingError<T>  {
    return {
        kind: 'error',

        failed: true,
        loading: false,
        present: false,

        error,
        message,
    };
}

export function loadingProgress<T>(oldData?: T): LoadingProgress<T> {
    return {
        kind: 'progress',

        failed: false,
        loading: true,
        present: false,
        data: oldData,
    };
}

export function loadedData<T>(t: T): LoadedData<T> {
    return {
        kind: 'loaded',

        failed: false,
        loading: false,
        present: true,
        data: t,
    };
}

export type LoadingState<T> = LoadingError<T> | LoadingProgress<T> | LoadedData<T>;
