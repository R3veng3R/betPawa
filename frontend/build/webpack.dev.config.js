const merge = require('webpack-merge');
const nodeExternals = require('webpack-node-externals');
const baseWebpack = require('./webpack.base.config');

const DEV_SERVER_PORT = 8080;
const API_SERVER_PORT = 9000;
const NODE_TEST_ENV = 'test';

module.exports = merge(baseWebpack, {
    mode: 'development',
    devtool: 'cheap-module-eval-source-map',

    devServer: {
        compress: true,
        historyApiFallback: true,
        hot: true,
        open: true,
        overlay: true,
        port: DEV_SERVER_PORT,

        proxy: [
            {
                context: ['/api'],
                target: 'http://localhost:' + API_SERVER_PORT,
                changeOrigin: true
            }
        ]
    },
});

// test specific setups
if (process.env.NODE_ENV === NODE_TEST_ENV) {
    module.exports.externals = [nodeExternals()];
    module.exports.devtool = 'eval'
}
