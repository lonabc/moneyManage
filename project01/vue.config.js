const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  lintOnSave: false,

  transpileDependencies: [
    'quasar'
  ],

  devServer:{
    host: '0.0.0.0',
    port:8000,
    client:{
      overlay:false
    },
    
  },

  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: true
    }
  }
})
