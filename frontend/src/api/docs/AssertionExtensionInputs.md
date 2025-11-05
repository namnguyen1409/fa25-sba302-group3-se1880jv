
# AssertionExtensionInputs


## Properties

Name | Type
------------ | -------------
`appid` | [AppId](AppId.md)
`largeBlob` | [LargeBlobAuthenticationInput](LargeBlobAuthenticationInput.md)
`prf` | [PrfAuthenticationInput](PrfAuthenticationInput.md)
`uvm` | boolean

## Example

```typescript
import type { AssertionExtensionInputs } from ''

// TODO: Update the object below with actual values
const example = {
  "appid": null,
  "largeBlob": null,
  "prf": null,
  "uvm": null,
} satisfies AssertionExtensionInputs

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AssertionExtensionInputs
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


