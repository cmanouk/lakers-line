const path = require('path');

module.exports = {
  mode: 'development',
  entry: path.resolve(__dirname, './index.js'),
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader'],
      }
    ],
  },
  resolve: {
    extensions: ['*', '.js', '.jsx'],
  },
  output: {
    path: path.resolve(__dirname, '../../your-favorite-lakers-backend/src/main/resources/static'),
    filename: 'bundle.js',
  }
};