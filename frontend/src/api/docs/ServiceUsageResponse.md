
# ServiceUsageResponse


## Properties

Name | Type
------------ | -------------
`serviceName` | string
`category` | string
`usedCount` | number
`totalPrice` | number

## Example

```typescript
import type { ServiceUsageResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "serviceName": null,
  "category": null,
  "usedCount": null,
  "totalPrice": null,
} satisfies ServiceUsageResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ServiceUsageResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


