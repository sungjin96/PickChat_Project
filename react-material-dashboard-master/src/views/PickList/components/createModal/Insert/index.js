import React from 'react';
import axios from 'axios';

const Insert = () => {
  const [state, setState] = React.useState({
    selectedFile: null
  });

  const onChange = e => {
    setState({
      selectedFile: e.target.files[0]
    });
  };

  const onClick = e => {
    const fd = new FormData();
    fd.append('uploadfile', state.selectedFile, state.selectedFile.name);
    axios
      .post('http://sungjin5891.cafe24.com/upload', fd, {
        onUploadProgress: ProgressEvent => {
          console.log(
            'Upload Progress: ' +
              (ProgressEvent.loaded / ProgressEvent.total) * 100 +
              '%'
          );
        }
      })
      .then(res => console.log(res));
  };

  return (
    <React.Fragment>
      <input type="file" onChange={onChange} />
      <button onClick={onClick}>업로드</button>
    </React.Fragment>
  );
};

export default Insert;
