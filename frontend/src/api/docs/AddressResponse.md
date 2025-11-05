
# AddressResponse


## Properties

Name | Type
------------ | -------------
`street` | string
`wardName` | string
`districtName` | string
`city` | string

## Example

```typescript
import type { AddressResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "street": null,
  "wardName": null,
  "districtName": null,
  "city": null,
} satisfies AddressResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AddressResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


