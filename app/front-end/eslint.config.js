import { defineConfig } from "eslint/config";
import globals from "globals";
import js from "@eslint/js";
import pluginVue from "eslint-plugin-vue";

export default defineConfig([
  { files: ["**/*.{js,mjs,cjs,vue}"] },
  { files: ["**/*.{js,mjs,cjs,vue}"], languageOptions: { globals: globals.browser } },
  {
    files: ["**/*.{js,mjs,cjs,vue}"],
    plugins: { js },
    extends: ["js/recommended"],
    rules: {
      "no-console": "warn", // Example: Warn on console.log
      "semi": ["error", "always"], // Example: Enforce semicolons
      "quotes": ["error", "double"], // Example: Enforce double quotes
      "indent": ["error", 2], 
      "comma-spacing": ["error", { before: false, after: true }],
      "vue/html-quotes": ["error", "double"],
    },
  },
  pluginVue.configs["flat/essential"],
]);