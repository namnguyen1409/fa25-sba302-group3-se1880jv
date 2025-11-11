
# CustomApiResponsePublicKeyCredentialCreationOptions


## Properties

Name | Type
------------ | -------------
`code` | number
`message` | string
`data` | [PublicKeyCredentialCreationOptions](PublicKeyCredentialCreationOptions.md)
`timestamp` | Date
`path` | string

## Example

```typescript
import type { CustomApiResponsePublicKeyCredentialCreationOptions } from ''

// TODO: Update the object below with actual values
const example = {
  "code": null,
  "message": null,
  "data": null,
  "timestamp": null,
  "path": null,
} satisfies CustomApiResponsePublicKeyCredentialCreationOptions

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as CustomApiResponsePublicKeyCredentialCreationOptions
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


