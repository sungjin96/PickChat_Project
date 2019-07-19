import React, { useEffect, useState } from 'react';
import defaultAxios from 'axios';

const useAxios = (opts, axiosInstance = defaultAxios) => {
  const [state, setState] = useState({
    loading: true,
    error: null,
    data: null
  });
  if (!opts.url) {
    return;
  }
  useEffect(() => {
    axiosInstance(opts).then(data => [
      setState({
        ...state,
        loading: false,
        data
      })
    ]);
  }, []);

  return state;
};

export default useAxios;
